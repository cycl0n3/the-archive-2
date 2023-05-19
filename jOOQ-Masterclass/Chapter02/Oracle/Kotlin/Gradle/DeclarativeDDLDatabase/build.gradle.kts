import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.language.jvm.tasks.ProcessResources
import org.jooq.meta.jaxb.Logging
import org.jooq.meta.jaxb.Property
import org.jooq.meta.jaxb.MatchersTableType
import org.jooq.meta.jaxb.MatcherRule
import org.jooq.meta.jaxb.MatcherTransformType
import org.jooq.meta.jaxb.Matchers

plugins {
    id("application")
    id("org.springframework.boot") version "2.5.7"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("nu.studer.jooq") version "6.0.1"
    id("org.flywaydb.flyway") version "7.7.3"
    kotlin("jvm") version "1.6.0"
    kotlin("plugin.spring") version "1.6.0"
}

val schemaVersion by extra { "1" }

group = "com.classicmodels"
version = "1.0"
java.sourceCompatibility = JavaVersion.VERSION_17

application {
    mainClass.value("com.classicmodels.MainApplicationKt")
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    jooqGenerator("com.oracle.database.jdbc:ojdbc8")    
    jooqGenerator("com.oracle.database.jdbc:ucp")    
    jooqGenerator("org.jooq.trial-java-8:jooq-meta-extensions")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.oracle.database.jdbc:ojdbc8")
    implementation("com.oracle.database.jdbc:ucp")
    implementation("org.flywaydb:flyway-core")
}

tasks {
  "processResources"(ProcessResources::class) {
    filesMatching("application.properties") {
      expand(project.properties)
    }
  }
}

flyway {
    driver = project.properties["driverClassName"].toString()
    url = project.properties["url"].toString()
    user = project.properties["username"].toString()
    password = project.properties["password"].toString()
    locations = arrayOf("filesystem:./../../../../../db/migration/ddl/oracle/sql",
                        "filesystem:./../../../../../db/migration/ddl/oracle/data")
    baselineOnMigrate = true
}

jooq {
  version.set(project.properties["jooq"].toString())
  edition.set(nu.studer.gradle.jooq.JooqEdition.TRIAL_JAVA_8)
  
  configurations {
        create("main") {  // name of the jOOQ configuration
            generateSchemaSourceOnCompilation.set(true)  // default (can be omitted)

            jooqConfiguration.apply {
                logging = Logging.WARN
                
                generator.apply {
                
                    // The default code generator. 
                    // You can override this one, to generate your own code style.
                                     
                    // Supported generators:                                
                    //  - org.jooq.codegen.JavaGenerator
                    //  - org.jooq.codegen.ScalaGenerator
                    //  - org.jooq.codegen.KotlinGenerator
                           
                    // Defaults to org.jooq.codegen.JavaGenerator
                    name = "org.jooq.codegen.KotlinGenerator"
                    
                    database.apply {
                        // Rely on jOOQ DDL Database API
                        name = "org.jooq.meta.extensions.ddl.DDLDatabase"
                        
                        // H2 database schema
                        inputSchema = "PUBLIC" 
                        
                        properties.add(
                           // Specify the location of your SQL script.
                           // You may use ant-style file matching, e.g. /path/**/to/*.sql
                           // 
                           // Where:
                           // - ** matches any directory subtree
                           // - * matches any number of characters in a directory / file name
                           // - ? matches a single character in a directory / file name
                        Property().withKey("scripts").withValue("./../../../../../db/migration/ddl/oracle/sql"))

                        properties.add(
                           // The sort order of the scripts within a directory, where:
                           // 
                           // - semantic: sorts versions, e.g. v-3.10.0 is after v-3.9.0 (default)
                           // - alphanumeric: sorts strings, e.g. v-3.10.0 is before v-3.9.0
                           // - flyway: sorts files the same way as flyway does
                           // - none: doesn't sort directory contents after fetching them from the directory                           
                           Property().withKey("sort").withValue("semantic"))

                        properties.add(
                           // The default schema for unqualified objects:
                           // 
                           // - public: all unqualified objects are located in the PUBLIC (upper case) schema
                           // - none: all unqualified objects are located in the default schema (default) 
                           // 
                           // This configuration can be overridden with the schema mapping feature
                           Property().withKey("unqualifiedSchema").withValue("none"))
   
                        properties.add(   
                           // The default name case for unquoted objects:
                           // 
                           // - as_is: unquoted object names are kept unquoted
                           // - upper: unquoted object names are turned into upper case (most databases)
                           // - lower: unquoted object names are turned into lower case (e.g. PostgreSQL)
                           Property().withKey("defaultNameCase").withValue("as_is"))

                        // All elements that are generated from your schema
                        // (A Java regular expression. Use the pipe to separate several expressions)
                        // Watch out for case-sensitivity. Depending on your database, this might be important! 
                        // You can create case-insensitive regular expressions using this syntax: (?i:expr).
                        // Whitespace is ignored and comments are possible.
                        includes = ".*"                        
                        
                        // All elements that are excluded from your schema
                        // (A Java regular expression. Use the pipe to separate several expressions).
                        // Excludes match before includes, i.e. excludes have a higher priority.
                        excludes = """
                                     flyway_schema_history | DEPARTMENT_PKG | GET_.*
                                   | CARD_COMMISSION | PRODUCT_OF_PRODUCT_LINE  
                                   | REFRESH_TOP3_PRODUCT | SALE_PRICE | SECOND_MAX
                                   | SET_COUNTER | SWAP | TOP_THREE_SALES_PER_EMPLOYEE
                                   | EVALUATION_CRITERIA | SECOND_MAX_IMPL | TABLE_.*_OBJ
                                   | .*_MASTER | BGT | .*_ARR | TABLE_POPL | TABLE_RES
                                  """
                                  
                        // Schema version provider
                        schemaVersionProvider = schemaVersion
                    }
                    
                    generate.apply {
                        isDeprecated = false
                        isRecords = true
                        isDaos = true
                        isValidationAnnotations = true
                        isSpringAnnotations = true
                    }
                    
                    strategy.withMatchers(Matchers()
                            .withTables(arrayOf(
                               MatchersTableType()
                                 .withPojoClass(MatcherRule()
                                      .withExpression("Jooq_$0")
                                      .withTransform(MatcherTransformType.PASCAL)),
                               MatchersTableType()
                                 .withDaoClass(MatcherRule()
                                      .withExpression("$0_Repository")
                                      .withTransform(MatcherTransformType.PASCAL))
                            ).toList()))
                            
                    target.apply {
                        packageName = "jooq.generated"
                        directory = "build/generated-sources"
                    }                    
                }
            }
        }
    }
}

// Configure jOOQ task such that it only executes when something has changed 
// that potentially affects the generated JOOQ sources:
// - the jOOQ configuration has changed (Jdbc, Generator, Strategy, etc.)
// - the classpath used to execute the jOOQ generation tool has changed 
//   (jOOQ library, database driver, strategy classes, etc.)
// - the schema files from which the schema is generated and which is 
//   used by jOOQ to generate the sources have changed (scripts added, modified, etc.)
tasks.named<nu.studer.gradle.jooq.JooqGenerate>("generateJooq") {
    // ensure database schema has been prepared by Flyway before generating the jOOQ sources
    dependsOn("flywayMigrate")

    // declare Flyway migration scripts as inputs on the jOOQ task
    inputs.files(fileTree("${rootDir}/../../../../../db/migration/ddl/oracle/sql"))
        .withPropertyName("migrations")
        .withPathSensitivity(PathSensitivity.RELATIVE)

    // make jOOQ task participate in incremental builds and build caching
    allInputsDeclared.set(true)
    outputs.cacheIf { true }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}
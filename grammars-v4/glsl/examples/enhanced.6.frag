#version 450 core
layout (location = 0) out vec4 FragColor;
layout (location = 0) in VS_OUT
{
vec2 TexCoords;
} fs_in[1];
layout (binding = 1) uniform sampler2D t0;
void main()
{
FragColor = texture(t0, fs_in[0].TexCoords);
}

import './App.css'



import React from 'react'
import {useState, useEffect} from 'react'


function About(props) {

    const [state, setstate] =useState("navstart")

    useEffect(()=>{

        if(props.aboutstate==="popstatestart"){

      setTimeout(()=>{
        props.setaboutstate("popstateend")

      }, 10)     
        }

        else{

  const timer =     setTimeout(()=>{

        if (props.aboutstate==="navstart"){
            props.setaboutstate("navend")
           }
    
           else if(props.state[1]==='navendfull'){
    
            props.setaboutstate("navendfull")
           }
    
    
           else{
        }

       

       }, 10)

       return ()=>{
        clearTimeout(timer)
        
       }
    }
    })

    
    return ( 

        
<div className={props.aboutstate}>
<div className="home">

<h1 className = "h1">One Stop Shop</h1>

<div className="homediv">
<p className='homep'>

We are team of two talented developers with an aim to develop web user interfaces for future. We keep our eye on the evolving nature of the web development. We see frontend development specially single page app is the technology of the future and we can achieve many special things which were not possible before.

<br></br>
Contact us @ contact@musk-technology.com
</p>

</div>


</div>
</div>



     );
}

export default About;
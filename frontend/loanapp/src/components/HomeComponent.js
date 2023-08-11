import React, { useEffect, useState } from 'react'

function HomeComponent({user}) {

  const [display,setDisplay] = useState("")
  
  useEffect(() =>{ 
    if(user!=null && user.length > 0)
    {
      setDisplay(`Hello ${user}`);
    }
    else
    {
      setDisplay("Hello Guest User, Please Login!");
    }

  },[user]);


  return (
    <div>{display}</div>
  )
}

export default HomeComponent
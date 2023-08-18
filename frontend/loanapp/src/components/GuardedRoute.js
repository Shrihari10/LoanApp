import React from 'react'
import { Navigate, Outlet, Route,  } from 'react-router-dom'

export default function GuardedRoute({isAuth}) {
  return isAuth? <Outlet/> : <Navigate to="/login"/>;
}

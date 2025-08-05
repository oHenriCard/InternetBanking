
import React from 'react';
import { Navigate, Outlet } from 'react-router-dom';

const useAuth = () => {
  const token = localStorage.getItem('authToken');
  return token != null;
};

const RotaProtegida = () => {
  const isAuth = useAuth();

  return isAuth ? <Outlet /> : <Navigate to="/" replace />;
};

export default RotaProtegida;
import React from 'react';
import { Link } from 'react-router-dom';  // Ensure you have react-router-dom for Link
import logo from '../../assets/logo.png'; // Update the path
import './MainPage.css';  // Ensure you have a CSS file for MainPage styling

const MainPage = () => (
  <div className="main-layout">
    {/* Add the logo image */}
    <img src={logo} alt="Logo" className="logo-top-right" />

    <div className="sidebar">
      <h2>Management System</h2>
      <ul>
        <li><Link to="/producer">Producer</Link></li>
        <li><Link to="/organization">Organization</Link></li>
        <li><Link to="/division">Division</Link></li>
        <li><Link to="/login">Login</Link></li>
      </ul>
    </div>
    <div className="content">
      <h1>Welcome to the Management System</h1>
      <p>Select an option from the sidebar to manage forms or log in.</p>
    </div>
  </div>
);

export default MainPage;

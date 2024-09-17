// src/components/Header/Header.js
import React from 'react';
import { Link } from 'react-router-dom';

function Header() {
  return (
    <header className="App-header">
      <h1>User Management System</h1>
      <nav>
        <ul>
          <li><Link to="/">Home</Link></li>
          <li><Link to="/producer">Producer</Link></li>
          <li><Link to="/organization">Organization</Link></li>
          <li><Link to="/division">Division</Link></li>
        </ul>
      </nav>
    </header>
  );
}

export default Header;

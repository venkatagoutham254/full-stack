import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './LoginComponent.css';  // Import the CSS
import logo from '../../assets/logo.png'; // Update the path

const LoginComponent = ({ onLogin }) => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleLogin = () => {
    let role = '';

    // Handling different roles based on email and password
    if (email === 'superadmin@aforo.ai' && password === 'aforo') {
      role = 'super_admin';
    } else if (email === 'vieweradmin@aforo.ai' && password === 'aforo') {
      role = 'viewer_admin';
    } else if (email === 'produceradmin@aforo.ai' && password === 'aforo') {
      role = 'producer_admin';
    } else if (email === 'customer@aforo.ai' && password === 'aforo') {
      role = 'customer';
    } else if (email === 'regularuser@aforo.ai' && password === 'aforo') {
      role = 'regular_user';
    } else {
      setError('Invalid email or password.');
      return;
    }

    // Passing role and login status back to the parent component
    onLogin(true, role);
    navigate('/dashboard'); // Redirecting to the dashboard after successful login
  };

  return (
    <div className="login-container">
      <img src={logo} alt="Logo" className="login-logo" />  {/* Image */}
      <h2>Login</h2>
      {error && <p className="error">{error}</p>}
      <input
        type="email"
        placeholder="Enter email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
      />
      <input
        type="password"
        placeholder="Enter password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />
      <button onClick={handleLogin}>Login</button>
    </div>
  );
};

export default LoginComponent;

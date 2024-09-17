import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import LoginComponent from './components/LoginComponent/LoginComponent';
import ProducerForm from './components/ProducerForm/ProducerForm';
import OrganizationForm from './components/OrganizationForm/OrganizationForm';
import DivisionForm from './components/DivisionForm/DivisionForm';
import MainPage from './components/MainPage/MainPage';

import SuperAdminDashboard from './dashboards/SuperAdminDashboard';
import ViewerAdminDashboard from './dashboards/ViewerAdminDashboard';
import ProducerAdminDashboard from './dashboards/ProducerAdminDashboard';
import CustomerDashboard from './dashboards/CustomerDashboard';
import RegularUserDashboard from './dashboards/RegularUserDashboard';
import './App.css';

const App = () => {
  const [producers, setProducers] = useState([]);
  const [organizations, setOrganizations] = useState([]);
  const [divisions, setDivisions] = useState([]);
  const [users, setUsers] = useState([
    {
      id: 1,
      name: "gowtham",
      email: "gowtham@gmail.com",
      password: "password123",
      subscriptions: [
        { id: 1, name: "Netflix", status: "Active" },
        { id: 2, name: "Amazon Prime", status: "Expired" },
      ],
    },
    {
      id: 2,
      name: "regular_user",
      email: "regular@gmail.com",
      password: "password456",
    },
  ]); // List of users (e.g., customers, regular users)
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [userRole, setUserRole] = useState(null);

  const addProducer = (producer) => setProducers([...producers, producer]);
  const addOrganization = (organization) => setOrganizations([...organizations, organization]);
  const addDivision = (division) => setDivisions([...divisions, division]);

  const updateProducer = (id, updatedProducer) => {
    setProducers((prevProducers) =>
      prevProducers.map((producer) => (producer.id === id ? { ...producer, ...updatedProducer } : producer))
    );
  };

  const updateOrganization = (id, updatedOrganization) => {
    setOrganizations((prevOrganizations) =>
      prevOrganizations.map((organization) => (organization.id === id ? { ...organization, ...updatedOrganization } : organization))
    );
  };

  const updateDivision = (id, updatedDivision) => {
    setDivisions((prevDivisions) =>
      prevDivisions.map((division) => (division.id === id ? { ...division, ...updatedDivision } : division))
    );
  };

  // Function to update user account details
  const updateAccount = (id, updatedUser) => {
    setUsers((prevUsers) =>
      prevUsers.map((user) => (user.id === id ? { ...user, ...updatedUser } : user))
    );
  };

  const deleteProducer = (id) => {
    setProducers((prevProducers) => prevProducers.filter((producer) => producer.id !== id));
  };

  const deleteOrganization = (id) => {
    setOrganizations((prevOrganizations) => prevOrganizations.filter((organization) => organization.id !== id));
  };

  const deleteDivision = (id) => {
    setDivisions((prevDivisions) => prevDivisions.filter((division) => division.id !== id));
  };

  const handleLogin = (isLoggedIn, role) => {
    setIsLoggedIn(isLoggedIn);
    setUserRole(role);
  };

  // Function to get the dashboard based on the role of the logged-in user
  const getDashboardComponent = () => {
    switch (userRole) {
      case 'super_admin':
        return <SuperAdminDashboard producers={producers} users={users} updateProducer={updateProducer} deleteProducer={deleteProducer} />;
      case 'viewer_admin':
        return <ViewerAdminDashboard producers={producers} />;
      case 'producer_admin':
        return (
          <ProducerAdminDashboard
            producers={producers}
            organizations={organizations}
            divisions={divisions}
            updateOrganization={updateOrganization}
            updateDivision={updateDivision}
            deleteOrganization={deleteOrganization}
            deleteDivision={deleteDivision}
          />
        );
      case 'customer':
        return <CustomerDashboard user={users[0]} updateAccount={updateAccount} />; // Pass the customer user
      case 'regular_user':
        return <RegularUserDashboard user={users[1]} updateAccount={updateAccount} />; // Pass the regular user
      default:
        return <LoginComponent onLogin={handleLogin} />;
    }
  };

  return (
    <Router>
      <Routes>
        <Route path="/" element={<MainPage />} />
        <Route path="/producer" element={<ProducerForm producers={producers} addProducer={addProducer} />} />
        <Route path="/organization" element={<OrganizationForm producers={producers} organizations={organizations} addOrganization={addOrganization} />} />
        <Route path="/division" element={<DivisionForm organizations={organizations} divisions={divisions} addDivision={addDivision} />} />
        <Route path="/login" element={<LoginComponent onLogin={handleLogin} />} />
        {isLoggedIn && <Route path="/dashboard" element={getDashboardComponent()} />}
      </Routes>
    </Router>
  );
};

export default App;

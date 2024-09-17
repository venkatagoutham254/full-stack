// src/components/UserList/UserList.js
import React from 'react';

function UserList({ users }) {
  return (
    <div className="user-list">
      <h2>User List</h2>
      <ul>
        {users.map((user,key) => (
          <li key={user.id || user.name}>  {/* Use user.id if available, or fallback to user.name */}
            <h3>{user.title || user.name}</h3>  {/* Name or title */}
            <p>{user.body || user.email}</p>    {/* Body or email */}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default UserList;

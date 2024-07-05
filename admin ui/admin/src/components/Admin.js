import React from "react";
import './Admin.css'

const Admin = () =>{

    var username = localStorage.getItem("username");
     username = username.split('@')[0];
    return(<div className="Admin_container">
        <div className="div_admin">
        <h1>Admin Portel</h1>
        <h2>Welcome: {username}</h2>
        <p>An admin management portal allows administrators to efficiently manage employees and Vendeord. 
            It provides tools for creating, updating, and deleting user accounts, 
            and assigning roles and permissions to control access levels. The portal enables role-based access control
             to ensure security .</p>
        </div>
        
    </div>
    );
}

export default Admin;



import React,{useState,useEffect} from "react";
import axios from "axios";
import "./Employee.css"
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const Employee = () =>{
 
const [users , setUsers] = useState([]);



    const handleLogin = async (e) => {
        const token = localStorage.getItem("token");
        try {
            const response = await axios.get('http://localhost:8080/user/get/UserDetails', {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });
            if (response.status === 200) {

                const usersData = response.data; // Assuming the token is returned in the response data
                setUsers(usersData);
                toast.success("Loding User Details");
               
            } else {
              toast.success("user Details NOt Found");
            
            }
        } catch (error) {
            console.error('user Details:', error);
        }
    };
  
    const updateUserDetails = async (userData) => {
      
      const token = localStorage.getItem("token");
        try { 
            const response = await axios.put('http://localhost:8080/admin/update/User', userData, {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });
            if (response.status === 200) {

                const usersData = response.data; // Assuming the token is returned in the response data
                toast.success(`${usersData}`);
               
            } else {
              toast.success("user Details NOt Found");
            
            }
        } catch (error) {
            console.error('user Details:', error);
        }
    };
    const handleDelete = async (userId) => {
      const token = localStorage.getItem("token");
  
      try {
        
        const response = await axios.get(
          `http://localhost:8080/admin/delete/user/${userId}`,
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );
        if (response.status === 200) {
          toast.error(response.data);
          setUsers(users.filter((user) => user.id !== userId));
        } else {
          toast.error(response.data);
        }
      } catch (error) {
        console.error("Delete user:", error);
      }
    };

    const handleSendMessage = async (userId) => {
      const token = localStorage.getItem("token");
  
      try {
        
        const response = await axios.get(
          `http://localhost:8080/vendor/send/message/${userId}`,
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );
        if (response.status === 200) {
          toast.error(response.data);
          setUsers(users.filter((user) => user.id !== userId));
        } else {
          toast.error(response.data);
        }
      } catch (error) {
        console.error("Delete user:", error);
      }
    };

    useEffect(() => {
      // Fetch user details initially
      handleLogin();
    } , []);// Run only once when the component mounts

   
    const handleRoleChange = (userId, newRole) => {
      setUsers(users.map((user) => {
        if (user.id === userId) {
          return { ...user, roles: newRole };
        }
        return user;
      }));
    };

    return(
        <div className="container">
          <a className="add-button" href="/AddEmployee"><button >ADD USERS</button></a>
      
      <div className="table-container"></div>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Email</th>
            <th>ctc</th>
            <th>designation</th>
            <th>upi</th>
            <th>Roles</th>
            <th> Update Roles</th>
            <th> Remove USER</th>
            <th>< h3>MESSAGE TO VENDOR</h3> </th>
          </tr>
        </thead>
        <tbody>
        {users.map(userv => (
            <tr key={userv.id}>
              <td>{userv.id}</td>
              <td>{userv.userName}</td>
              <td> {userv.email}</td>
              <td> {userv.ctc}</td>
              <td> {userv.designation}</td>
              <td> {userv.upi}</td>
              <td><input value={userv.roles}  onChange={(e) => handleRoleChange(userv.id, e.target.value)}/></td>
              <td><button className="update" onClick={() => updateUserDetails(userv) }>Update </button></td>
              <td><button className="delete" onClick={() => handleDelete(userv.id)}>Delete</button></td>
              
              <td> {userv.roles === 'ROLE_EMPLOYEE' ? (
    <button className="sendMessageToVendor" disabled>Send Message</button>
  ) : (
    <button className="sendMessageToVendor" onClick={() => handleSendMessage(userv.id)}>Send Message</button>
  )}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <ToastContainer />
    </div>
    );
}

export default Employee;
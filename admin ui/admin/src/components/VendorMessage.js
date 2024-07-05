


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
            const response = await axios.get('http://localhost:8080/vendor/fetching/message', {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });
            if (response.status === 200) {

                const usersData = response.data; // Assuming the token is returned in the response data
                setUsers(usersData);
                toast.success("Loding vendor Message Details");
               
            } else {
              toast.success("vendor Message Details NOt Found");
            
            }
        } catch (error) {
            console.error('vendor Message Details:', error);
        }
    };
  
 

    useEffect(() => {
      // Fetch user details initially
      handleLogin();
    } , []);// Run only once when the component mounts

   
   

    return(
        <div className="container">
      
      <div className="table-container"></div>
      <table>
        <thead>
          <tr>
            <th>id</th>
            <th>Username</th>
            <th>Email</th>
            <th>vendor_template</th>
            <th>vendor_upi</th>
          </tr>
        </thead>
        <tbody>
        {users.map(userv => (
            <tr key={userv.id}>
              <td>{userv.id}</td>
              <td>{userv.name}</td>
              <td> {userv.email}</td>
              <td> {userv.vendorTemplate}</td>
              <td> {userv.vendorUpi}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <ToastContainer />
    </div>
    );
}

export default Employee;
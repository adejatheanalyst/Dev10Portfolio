import {  useEffect, useState } from 'react'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import NavBar from './components/NavBar';
import './App.css';
import './styles.css';
import './index.css';
import UserForm from './components/UserForm';
import LoginForm from './components/LoginForm';
import MoodViceList from './components/MoodViceList'
import Resources from './components/Resources';
import { Navigate } from 'react-router-dom';
import MoodMedia from './components/MoodMedia';
import MoodCharts from './components/MoodCharts';
import CreateMoodVice from './components/CreateMoodVice';
import ReplyList from './components/ReplyList';
import UserAccount from './components/UserAccount';
import Home from './components/Home';
import EditReply from './components/EditReply';
import {Cloudinary} from "@cloudinary/url-gen";
import {fill} from "@cloudinary/url-gen/actions/resize";




function App() {

  const[loggedIn, setLoggedIn] = useState(null);
  const[moodId, setMoodId] = useState(0);
  
  const[hasCheckedForUser, setHasCheckedForUser] = useState(false);
  useEffect(() =>{
    const loggedInUser = localStorage.getItem("loggedIn");
    if(loggedInUser){
      setLoggedIn(JSON.parse(loggedInUser));
    }
    setHasCheckedForUser(true);
  }
, []);
  if(!hasCheckedForUser){
    return <p>Loading...</p>
  }
      const cld = new Cloudinary({
        cloud: {
          cloudName: 'ddugswahb'
        }
      });
      const myImage = cld.image('moodvice-transparent_l1r0dd');
      myImage.resize(fill().width(50).height(50));

  return (
    <div>
      <Router>
        <div>
          <div class="navBar">
           

          <NavBar loggedIn={loggedIn} setLoggedIn={setLoggedIn}/>
          </div>
          <div class ="container">
          <main>
            <Routes>
              <Route path="/" element={<Home loggedIn={loggedIn}/>}/>
              <Route path="/signup" element={loggedIn !== null ? <h1>You are already logged in</h1> : <UserForm setLoggedIn={setLoggedIn} />}/>
              <Route path="/enterMood" element={loggedIn === null ? <Navigate to ="/login" /> : <Home loggedIn={loggedIn} />}/>
              <Route path="/login" element={loggedIn !== null ? <Navigate to ="/myAccount" />: <LoginForm setLoggedIn={setLoggedIn}/>}/>
              <Route path="/:moodId" element={loggedIn === null ? <Navigate to ="/login" />:<MoodMedia />}/>
              <Route path="/moodVice" element={loggedIn === null ? <Navigate to ="/login" />:<MoodViceList loggedIn={loggedIn}/>}/>
              <Route path="/addMoodVice" element={loggedIn === null ? <Navigate to ="/login" />:<CreateMoodVice loggedIn={loggedIn} setLoggedIn={setLoggedIn}/>}/>
              <Route path="/edit/:moodViceId" element={loggedIn === null ? <Navigate to ="/login" />:<CreateMoodVice loggedIn={loggedIn} setLoggedIn={setLoggedIn}/>}/>
              <Route path="/resources" element={<Resources />}/>
              <Route path="/edit/:moodViceId/:replyId" element={loggedIn === null ? <Navigate to ="/login" />:<EditReply loggedIn={loggedIn} />}/>
              <Route path="/reply/:moodViceId" element={loggedIn === null ? <Navigate to ="/login" />:<ReplyList loggedInUser={loggedIn} />}/>
              <Route path="/charts" element={loggedIn === null ? <Navigate to ="/login" />:<MoodCharts loggedIn={loggedIn} />}/>
              <Route path="/myAccount" element={loggedIn === null ? <Navigate to ="/login" />:<UserAccount loggedIn={loggedIn} setLoggedIn={setLoggedIn}/>}/>
            </Routes>
          </main>
        </div>
        </div>
      </Router>
    </div>
  )
}

export default App

import { useState } from 'react'
// import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import plumbob from './assets/plumbob.png'
import './App.css'
import TopNavBar from './components/TopNavBar'
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom'
import QuestionList from './components/QuestionList'
import AnswerList from './components/AnswerList'

function App() {
  const [questions, setQuestions] = useState([])
  const [answers, setAnswers] = useState([])

  return (
    <Router>
      <div>
        <TopNavBar/>
        <main>
          <Routes>
            <Route path="/" element={<QuestionList/>}/>
            <Route path="/answers/questionId/:questionId" element={<AnswerList questions={questions} setQuestions={setQuestions}/>}/>
          </Routes>
        </main>
      </div>
    </Router>

  );
}
export default App

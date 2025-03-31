import { useParams } from "react-router-dom"
import { useEffect, useState, useCallback, use } from "react";
import React from 'react';
import {  ScatterChart,
    BarChart,
    Bar,
    Scatter,
    XAxis,
    YAxis,
    ZAxis,
    CartesianGrid,
    Tooltip,
    Legend,
    ResponsiveContainer, } from 'recharts';


export default function UserMoodsGraph({loggedIn, formattedDate}) {
    const [userMoods, setUserMoods] = useState([]);
    const params = useParams()
          useEffect(() => {
            fetch(`http://localhost:8080/api/userMood/myMoods/${formattedDate}`, {
                method: "GET",
                headers: {
                    Authorization: loggedIn.userId
                }
            })
            .then(res => {
                if(res.status === 401) {
                    setloggedIn(null)
                    localStorage.clear("loggedIn")
                }
                return res.json()
            })
            .then(fetchedUserMoods => {
              setUserMoods(fetchedUserMoods)
            })
        }, [formattedDate, loggedIn.userId])

    
    const moodCounts = [
        { name: "Happy", MoodsPerDay: 0, Notes: "" },
        { name: "Sad", MoodsPerDay: 0, Notes: ""},
        { name: "Angry", MoodsPerDay: 0, Notes: ""},
        { name: "Anxious", MoodsPerDay: 0, Notes: ""},
        { name: "Calm", MoodsPerDay: 0, Notes: ""},
        { name: "Humorous", MoodsPerDay: 0, Notes: ""},
        { name: "Fearful", MoodsPerDay: 0, Notes: ""},
    ];

    userMoods.forEach(userMood => {
        if (userMood.moodId === 1) 
            moodCounts[0].MoodsPerDay++;
        if (userMood.moodId === 2) 
            moodCounts[1].MoodsPerDay++;
        if (userMood.moodId === 3) 
            moodCounts[2].MoodsPerDay++;
        if (userMood.moodId === 4) 
            moodCounts[3].MoodsPerDay++;
        if (userMood.moodId === 5) 
            moodCounts[4].MoodsPerDay++;
        if (userMood.moodId === 6) 
            moodCounts[5].MoodsPerDay++;
        if (userMood.moodId === 7) 
            moodCounts[6].MoodsPerDay++;
    });

    const data = moodCounts;
 
    return (
        <div>
            {userMoods.length > 0  ? null : <p className="errors">No moods available for this date.</p>}
            <ResponsiveContainer width={610} height={300}>
            <BarChart width={560} height={300} data={data}
            margin={{ top: 5, right: 30, left: 20, bottom: 5 }}>
                <CartesianGrid  />
                    <XAxis dataKey="name" stroke="#4d0099" /> 
                    <YAxis dataKey="MoodsPerDay"  />
                    <Tooltip />
                    <Legend dataKey="Notes" />
                    <Bar dataKey="MoodsPerDay" fill="#6a11cb" barSize={45} background={{ fill: '#1a1a1a' }} />
            </BarChart>
            </ResponsiveContainer>
        </div>
    );
}


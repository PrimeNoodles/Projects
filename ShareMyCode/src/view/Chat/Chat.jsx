import React, { useEffect, useState } from "react";
import './Chat.css';

export default function Chat(props)
{
    const [chatMsg, setChatMsg] = useState("");
    const [messages, setMessages] = useState([]);

    const onSubmit = (event) =>
    {
        event.preventDefault();
        event.target.reset();
        // Emit to send message event
        props.socket.emit("send message", chatMsg, props.userName, props.socket.id);
    };

    const onChange = (event) =>
    {
        setChatMsg(event.target.value);
    };

    const onClick = (event, recipient) =>
    {
        event.preventDefault();
        props.socket.emit("send private msg", "", props.userName, recipient);
    };

    useEffect(() =>
    {
        // Listen for response from message sent event
        props.socket.on("message sent", (chatMsg, player, socketID) =>
        {
            setMessages(prevMessages => [...prevMessages, { player: { name: player, id: socketID }, chatMsg }]);
        });

        props.socket.on("private msg sent", (chatMsg) =>
        {
            setMessages(prevMessages => [...prevMessages, { player: { name: "CONSOLE", id: "0"}, chatMsg }])
        });

        props.socket.on("player join", (msg) =>
        {
            setMessages(prevMessages => [...prevMessages, { player: { name: "CONSOLE", id: "0"}, chatMsg: msg }]);
        });

        props.socket.on("player leave", (msg) =>
        {
            setMessages(prevMessages => [...prevMessages, { player: { name: "CONSOLE", id: "0"}, chatMsg: msg }]);
        });

        // Clean up the effect
        return () => props.socket.disconnect();
    }, []);

    return (
        <div className="Chat">
           <span className="welcomemsg">  Welcome, <strong className="username">{props.userName}</strong> </span> 
            <form onSubmit={onSubmit}>
                <input type="text" placeholder="Chat Here" onChange={onChange} />
            </form>
            Messages:<br/>
            {messages.map(msg => 
                <div className="otherUser">
                    {msg.player.name !== props.userName ? 
                        <a href={msg.player.name} onClick={event => onClick(event, msg.player)}><strong className="playername">{msg.player.name}  </strong></a> : 
                        <strong className="username">{msg.player.name}</strong>
                    }  
                   : &nbsp; 
                    {msg.chatMsg}
                </div>
            )}
        </div>
    );
}
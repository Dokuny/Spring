import React from 'react';
import Todo from './Todo';
import './App.css';
import {List, Paper} from "@material-ui/core";
import AddTodo from "./AddTodo";

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            items: [
                {id: "0", title: "hello world 1", done: true},
                {id: "1", title: "hello world 2", done: false},
            ],
        };
    }

    render() {
        let todoItems = this.state.items.length> 0 &&(
            <Paper style={{margin:16}}>
                <List>
                    {this.state.items.map((item,idx) => (
                        <Todo item={item} key={item.id}/>
                    ))}
                </List>
            </Paper>
        )
        return (
            <div className="App">
                <AddTodo />
                <div className="TodoList">{todoItems}</div>
            </div>
        );
    }
}

export default App;

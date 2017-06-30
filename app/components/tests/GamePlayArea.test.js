import React from 'react';
import { shallow, mount } from 'enzyme';
import renderer from 'react-test-renderer';
import GamePlayArea from './../GamePlayArea';
import { retriveShipLocation } from './../../actions';

// Snapshot for GamePlayArea React Component
describe('GamePlayArea --- Snapshot',()=>{
    const currentUser = {
        "message":"",
        "status":true,
        "statusCode":200,
        "gameId":"",
        "playerId":"",
        "playerName":""
    };
    const shipLocation = {"message":"","status":true,"statusCode":200,"gameId":"107","players":[{"id":"p1","name":"test","shipCoordinates":["22","23","24"],"readyToPlay":true},{"id":"p2","name":"tree","shipCoordinates":["63","64","65"],"readyToPlay":true}],"proceedGame":true};
    const data = {};
    it('capturing Snapshot of GamePlayArea', () => {
        const renderedValue =  renderer.create(<GamePlayArea currentUser={currentUser}
        retriveShipLocation={retriveShipLocation}
        shipLocation={shipLocation}
        data={data}
        />).toJSON()
        expect(renderedValue).toMatchSnapshot();
    });
});
//*******************************************************************************************************
describe('GamePlayArea --- Shallow Render REACT COMPONENTS',()=>{
    let wrapper;
    const currentUser = {
        "message":"",
        "status":true,
        "statusCode":200,
        "gameId":"",
        "playerId":"",
        "playerName":""
    };
    const shipLocation = {"message":"","status":true,"statusCode":200,"gameId":"107","players":[{"id":"p1","name":"test","shipCoordinates":["22","23","24"],"readyToPlay":true},{"id":"p2","name":"tree","shipCoordinates":["63","64","65"],"readyToPlay":true}],"proceedGame":true};
    const data = {};
    beforeEach(()=>{
        wrapper = mount(<GamePlayArea currentUser={currentUser}
        retriveShipLocation={retriveShipLocation}
        shipLocation={shipLocation}
        data={data}
        />)
        
    })

    it('should render the DUMB component', () => {
       expect(wrapper.length).toEqual(1)
    });

    
    
});

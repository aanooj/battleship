import React from 'react';
import renderer from 'react-test-renderer';
import PlaceMyShip from './../PlaceMyShip';
import {placeShip} from '../../actions';
// Snapshot for PlaceMyShip React Component
describe('Place My Ship --- Snapshot',()=>{
    
    beforeEach(()=>{
        let data = {"message":"","status":true,"statusCode":200,"gameId":"","playerId":"","playerName":""};
        global.sessionStorage.setItem('currentUser',JSON.stringify(data));
    })
    it('capturing Snapshot of PlaceMyShip', () => {
        const renderedValue =  renderer.create(<PlaceMyShip placeShip={placeShip} />).toJSON()
        expect(renderedValue).toMatchSnapshot();
    });

});

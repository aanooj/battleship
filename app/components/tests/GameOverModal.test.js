import React from 'react';
import { shallow } from 'enzyme';
import renderer from 'react-test-renderer';
import GameoverModal from './../GameOverModal';

import {startNew} from './../../actions'
// Snapshot for GameoverModal React Component
describe('GameoverModal --- Snapshot',()=>{
    it('capturing Snapshot of GameoverModal', () => {
        const winner = 'test';
        const renderedValue =  renderer.create(<GameoverModal 
                                                    startNew={startNew}
                                                    winner ={winner}/>).toJSON();
        expect(renderedValue).toMatchSnapshot();
    });
});
//*******************************************************************************************************
describe('GameoverModal --- Shallow Render REACT COMPONENTS',()=>{
    let wrapper;

    beforeEach(()=>{
        const winner = 'test';
        const startNewMock = jest.fn();
        wrapper = shallow(<GameoverModal  startNew={startNewMock}
                                          winner ={winner}/>)
        
    })

    it('should render the DUMB component', () => {
       expect(wrapper.length).toEqual(1)
    });
    it('should contains button ', () => {
        expect(wrapper.find('button').type()).toEqual('button')
    });
    it('should call startNewGame on click of button',() => {
       wrapper.startNewGame = jest.fn();
        wrapper.find('button').simulate('click');
        wrapper.startNewGame();
        expect(wrapper.startNewGame).toHaveBeenCalled();
    })    
});

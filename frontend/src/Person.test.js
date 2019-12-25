import React     from 'react'
import Enzyme    from 'enzyme'
import Adapter   from 'enzyme-adapter-react-16'
import {shallow} from 'enzyme'
import Person    from './Person'


Enzyme.configure({ adapter: new Adapter() })
let wrapper
beforeEach(() => {
   wrapper = shallow(<Person />)
});

it ('should exist', () => {
     expect(wrapper.length).toBe(1);
})

xit ('should get id from backend', () => {
     expect(wrapper.state().id).toBe(2)
})
;
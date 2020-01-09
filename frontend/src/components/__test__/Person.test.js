import React   from 'react'
import { render, fireEvent, screen, cleanup } from '@testing-library/react'
import '@testing-library/jest-dom/extend-expect'
import Person    from './../Person'
import fetchMock from 'fetch-mock'

const person1 = {
                  id: "1",
                  firstName : 'Stein',
                  familyName: 'Korsveien'
						}
const person2 = {
                  id: "2",
                  firstName : 'Oddmund',
                  familyName: 'Korsveien'
						}

const person3 = {
                  id: "1",
                  firstName : 'Sturlar',
                  familyName: 'Surlarson'
						}
						

beforeAll(() => {
 
  fetchMock.get('http://localhost:8080/person?1',JSON.stringify(person1), { status: 200 })
  fetchMock.get('http://localhost:8080/person?2',JSON.stringify(person2), { status: 200 })
  fetchMock.put('http://localhost:8080/person/update',JSON.stringify(person3), { status: 200 })
})

  
it ('should exist', () => {
   const { getByLabelText } = render(<Person />)
     expect(getByLabelText ).toBeTruthy();
})

it('should display enterd id', () => {
  const { getByLabelText } = render(<Person />)
   verifyInput(getByLabelText,'Id:','1')
})

it('should display enterd first name', () => {
  const { getByLabelText } = render(<Person />)
   verifyInput(getByLabelText,'First Name:','Stein')
})

it('should display enterd family name', () => {
  const { getByLabelText } = render(<Person />)
  verifyInput(getByLabelText,'Family Name:','Korsveien')
})


it ('should submit entered data',() => {
     const { getByText,getByLabelText } = render(<Person />)
   verifyInput(getByLabelText,'Id:','1')
   verifyInput(getByLabelText,'First Name:','Stein')
   verifyInput(getByLabelText,'Family Name:','Korsveien')
   const submit = getByText('Submit')
   fireEvent.click(submit)
   fetchMock.called()

})

it ('should get detail person data',() => {
   const { getByText, getByLabelText } = render(<Person />)
   verifyInput(getByLabelText,'Id:',person1.id)
   const get = getByText('Get')
   fireEvent.click(get)
   fetchMock.called()
   
   expect(getByLabelText('Id:').value).toBe(person1.id)
   expect(getByLabelText('First Name:').value).toBe(person1.firstName)
   expect(getByLabelText('Familiy Name:').value).toBe(person1.familyName)
})

afterEach(cleanup)

 function verifyInput(aFunction,aElement,aValue) { 
   const element = aFunction (aElement)
   fireEvent.change(element, {
    target: {value: aValue},
    })
   expect(element.value).toBe(aValue)    
}
import React, {
  useContext
} from 'react'

import { CountContext } from '../../App';

const ComponentA = () => {
  const countContext = useContext(CountContext)

  return (
    <div className='div'>
      Component A - {countContext.countState}
      <button onClick={() => countContext.countDispatch({ type: 'INCREMENT' })}>Increment</button>
      <button onClick={() => countContext.countDispatch({ type: 'DECREMENT' })}>Decrement</button>
      <button onClick={() => countContext.countDispatch({ type: 'RESET' })}>Reset</button>
    </div>
  )
}

export default ComponentA;

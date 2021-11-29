const IsAction = (button) => {
  const b={}
  button.forEach(item => {
    b[item]=true
  })
  return b
}

export {
  IsAction
}
import Mock from 'mockjs'

Mock.mock(`${process.env.VUE_APP_API_BASE_URL}/routes`, 'get', () => {
  let result = {}
  result.code = 0
  result.data = [{
    router: 'root',
    children: [
      {
        router: 'index',
        children: [],
      },
      {
        router: 'dashboard',
        children: ['banner', 'navigation','novice','kefu','agreement','rule'],
      },
      {
        router: 'position',
        children: ['classtype', 'highlights', 'job']
      },
      {
        router: 'signup',
        children: ['bm', 'recommend']
      },
      {
        router: 'strategy',
        children: ['strategylist']
      },
      {
        router: 'commission',
        children: ['grant', 'audit', 'income']
      },
      {
        router: 'member',
        children: ['company', 'personal']
      },
      {
        router: 'authorization',
        children: ['user', 'role']
      },
      {
        router: 'other',
        children: ['complaint']
      }
    ]
  }]
  return result
})

import { createStore } from 'vuex'

export default createStore({
  state: {
    currentUser: null,
    paperInfo: null
  },
  getters: {
    getCurrentUser: (state) => {
      if (!state.currentUser)
        state.currentUser = JSON.parse(sessionStorage.getItem("currentUser"))
      return state.currentUser
    },
    getPaperInfo: (state) => {
      if (!state.paperInfo)
        state.paperInfo = JSON.parse(sessionStorage.getItem("paperInfo"))
      return state.paperInfo
    }
  },
  mutations: {
    SET_CURRENT_USER: (state, payload) => {
      //刷新后state中的数据就没有了，所以对于当前用户数据来说可以考虑存到session中
      sessionStorage.setItem("currentUser",JSON.stringify(payload))
      state.currentUser = payload
    },
    setPaperInfo: (state, payload) => {
      sessionStorage.setItem("paperInfo",JSON.stringify(payload))
      state.paperInfo = payload
    }
  },
  actions: {
  },
  modules: {
  }
})

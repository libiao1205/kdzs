export default {
    addLangPack(state, data){
        if(data){
            state.langPack.push(data)
        }
    },
    setLayout(state, data){
        if(data){
            state.navbarPosition = data
        }
    },
    setThemeColor(state, data){
        state.theme = data
    }
}

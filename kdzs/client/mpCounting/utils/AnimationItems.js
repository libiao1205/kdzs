export default class AnimationItems {
  constructor() {
    this.timer = null;
    this.status = false;
    this.num = 3;// 每秒出现花瓣个数
    this.times = 3; // 每个出现时间
    this.start = function () {
      
      this.timer = setInterval(() => {
        
      }, 1000 / this.num);

    };
    this.stop = function () {
      
    };
  }
}

import { login, register } from '../../utils/api';

Page({
  data: {
    username: '',
    password: ''
  },

  onUsernameInput(e) {
    this.setData({ username: e.detail.value });
  },

  onPasswordInput(e) {
    this.setData({ password: e.detail.value });
  },

  async handleLogin() {
    const { username, password } = this.data;
    if (!username || !password) {
      wx.showToast({
        title: '请输入用户名和密码',
        icon: 'none'
      });
      return;
    }
    
    try {
      const user = await login(username, password);
      getApp().globalData.userId = user.id;
      wx.showToast({
        title: '登录成功',
        icon: 'success'
      });
      wx.navigateBack();
    } catch (error) {
      wx.showToast({
        title: '登录失败',
        icon: 'none'
      });
    }
  },

  async handleRegister() {
    const { username, password } = this.data;
    if (!username || !password) {
      wx.showToast({
        title: '请输入用户名和密码',
        icon: 'none'
      });
      return;
    }
    
    try {
      const user = await register(username, password);
      getApp().globalData.userId = user.id;
      wx.showToast({
        title: '注册成功',
        icon: 'success'
      });
      wx.navigateBack();
    } catch (error) {
      wx.showToast({
        title: '注册失败',
        icon: 'none'
      });
    }
  }
});
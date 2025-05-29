import { getUserInfo, login } from '../../utils/api';

Page({
  data: {
    userInfo: null
  },

  onShow() {
    this.checkLoginStatus();
  },

  checkLoginStatus() {
    const userId = getApp().globalData.userId;
    if (userId) {
      this.loadUserInfo(userId);
    } else {
      this.setData({ userInfo: null });
    }
  },

  async loadUserInfo(userId) {
    try {
      const userInfo = await getUserInfo(userId);
      this.setData({ userInfo });
    } catch (error) {
      wx.showToast({
        title: '获取用户信息失败',
        icon: 'none'
      });
    }
  },

  login() {
    wx.navigateTo({
      url: '/pages/login/login'
    });
  },

  viewOrders() {
    const userId = getApp().globalData.userId;
    if (!userId) {
      wx.showToast({
        title: '请先登录',
        icon: 'none'
      });
      return;
    }
    wx.navigateTo({
      url: `/pages/orders/orders?userId=${userId}`
    });
  },

  viewAddress() {
    wx.showToast({
      title: '功能开发中',
      icon: 'none'
    });
  },

  viewSettings() {
    wx.showToast({
      title: '功能开发中',
      icon: 'none'
    });
  }
});
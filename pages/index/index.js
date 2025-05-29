import { getAllDishes, searchDishes, addToCart } from '../../utils/api';

Page({
  data: {
    dishes: [],
    hotDishes: [],
    searchKeyword: ''
  },

  onLoad() {
    this.loadDishes();
  },

  async loadDishes() {
    try {
      const dishes = await getAllDishes();
      this.setData({
        dishes,
        hotDishes: dishes.slice(0, 2) // 取前2个作为热门推荐
      });
    } catch (error) {
      wx.showToast({
        title: '加载失败',
        icon: 'none'
      });
    }
  },

  onSearchInput(e) {
    this.setData({
      searchKeyword: e.detail.value
    });
  },

  async onSearchConfirm() {
    if (!this.data.searchKeyword) {
      this.loadDishes();
      return;
    }
    
    try {
      const dishes = await searchDishes(this.data.searchKeyword);
      this.setData({ dishes });
    } catch (error) {
      wx.showToast({
        title: '搜索失败',
        icon: 'none'
      });
    }
  },

  goToDetail(e) {
    const id = e.currentTarget.dataset.id;
    wx.navigateTo({
      url: `/pages/detail/detail?id=${id}`
    });
  },

  addToCart(e) {
    e.stopPropagation();
    const dishId = e.currentTarget.dataset.id;
    const userId = getApp().globalData.userId;
    
    if (!userId) {
      wx.showToast({
        title: '请先登录',
        icon: 'none'
      });
      return;
    }
    
    addToCart(userId, dishId, 1).then(() => {
      wx.showToast({
        title: '已加入购物车'
      });
    }).catch(err => {
      wx.showToast({
        title: '添加失败',
        icon: 'none'
      });
    });
  }
});
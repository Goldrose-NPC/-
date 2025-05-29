import { getDishesByCategory } from '../../utils/api';

// 模拟分类数据，实际项目中应该从后端获取
const categories = [
  { id: 1, categoryName: '热销' },
  { id: 2, categoryName: '主食' },
  { id: 3, categoryName: '小吃' },
  { id: 4, categoryName: '饮料' },
  { id: 5, categoryName: '套餐' }
];

Page({
  data: {
    categories,
    activeCategory: 1,
    dishes: []
  },

  onLoad() {
    this.loadDishes(1);
  },

  changeCategory(e) {
    const categoryId = e.currentTarget.dataset.id;
    this.setData({ activeCategory: categoryId });
    this.loadDishes(categoryId);
  },

  async loadDishes(categoryId) {
    try {
      const dishes = await getDishesByCategory(categoryId);
      this.setData({ dishes });
    } catch (error) {
      wx.showToast({
        title: '加载失败',
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

import { getCartItems, removeFromCart, createOrder } from '../../utils/api';

Page({
  data: {
    cartItems: [],
    totalPrice: 0
  },

  onShow() {
    this.loadCartItems();
  },

  async loadCartItems() {
    const userId = getApp().globalData.userId;
    if (!userId) {
      wx.showToast({
        title: '请先登录',
        icon: 'none'
      });
      return;
    }
    
    try {
      const cartItems = await getCartItems(userId);
      this.calculateTotal(cartItems);
      this.setData({ cartItems });
    } catch (error) {
      wx.showToast({
        title: '加载失败',
        icon: 'none'
      });
    }
  },

  calculateTotal(cartItems) {
    let total = 0;
    cartItems.forEach(item => {
      total += item.dish.dishPrice * item.quantity;
    });
    this.setData({ totalPrice: total.toFixed(2) });
  },

  decreaseQuantity(e) {
    const itemId = e.currentTarget.dataset.id;
    const cartItems = this.data.cartItems.map(item => {
      if (item.id === itemId && item.quantity > 1) {
        item.quantity--;
      }
      return item;
    });
    this.calculateTotal(cartItems);
    this.setData({ cartItems });
  },

  increaseQuantity(e) {
    const itemId = e.currentTarget.dataset.id;
    const cartItems = this.data.cartItems.map(item => {
      if (item.id === itemId) {
        item.quantity++;
      }
      return item;
    });
    this.calculateTotal(cartItems);
    this.setData({ cartItems });
  },

  removeItem(e) {
    const itemId = e.currentTarget.dataset.id;
    wx.showModal({
      title: '提示',
      content: '确定要删除该商品吗？',
      success: async (res) => {
        if (res.confirm) {
          try {
            await removeFromCart(itemId);
            this.loadCartItems();
          } catch (error) {
            wx.showToast({
              title: '删除失败',
              icon: 'none'
            });
          }
        }
      }
    });
  },

  async checkout() {
    const userId = getApp().globalData.userId;
    if (!userId) {
      wx.showToast({
        title: '请先登录',
        icon: 'none'
      });
      return;
    }
    
    if (this.data.cartItems.length === 0) {
      wx.showToast({
        title: '购物车为空',
        icon: 'none'
      });
      return;
    }
    
    try {
      await createOrder(userId);
      wx.showToast({
        title: '下单成功',
        icon: 'success'
      });
      this.loadCartItems();
    } catch (error) {
      wx.showToast({
        title: '下单失败',
        icon: 'none'
      });
    }
  }
});

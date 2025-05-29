import { getOrdersByUser } from '../../utils/api';

Page({
  data: {
    orders: []
  },

  onLoad(options) {
    this.userId = options.userId;
    this.loadOrders();
  },

  async loadOrders() {
    try {
      const orders = await getOrdersByUser(this.userId);
      this.setData({ orders });
    } catch (error) {
      wx.showToast({
        title: '加载失败',
        icon: 'none'
      });
    }
  },

  getStatusText(status) {
    const statusMap = {
      0: '已取消',
      1: '待支付',
      2: '已支付',
      3: '已完成'
    };
    return statusMap[status] || '未知状态';
  },

  formatTime(time) {
    return new Date(time).toLocaleString();
  }
});
import { getAllDishes } from '../../utils/api';

Page({
  data: {
    recommendDishes: [
      {id: 1, name: "酸辣土豆丝", price: 8, image: "../../assets/images/dish1.png"},
      {id: 2, name: "番茄炒蛋", price: 12, image: "../../assets/images/dish2.png"}
    ],
    dishes: [
      {id: 3, name: "宫保鸡丁", price: 15, image: "../../assets/images/dish3.png"},
      {id: 4, name: "蜜汁烤鸡", price: 38, image: "../../assets/images/dish4.png"},
      {id: 5, name: "麻辣小龙虾",price: 42.8, image: "../../assets/images/dish5.png"}
    ]
  }
})

 
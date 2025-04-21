/*
 Navicat Premium Data Transfer

 Source Server         : AR
 Source Server Type    : MySQL
 Source Server Version : 80034
 Source Host           : localhost:3306
 Source Schema         : ar

 Target Server Type    : MySQL
 Target Server Version : 80034
 File Encoding         : 65001

 Date: 21/04/2025 13:03:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for collaborator
-- ----------------------------
DROP TABLE IF EXISTS `collaborator`;
CREATE TABLE `collaborator`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `scene_id` bigint(0) NOT NULL COMMENT '场景ID',
  `user_id` bigint(0) NOT NULL COMMENT '协作者ID',
  `permission_level` enum('view','edit','admin') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'view',
  `invited_at` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `scene_user_unique`(`scene_id`, `user_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `collaborator_ibfk_1` FOREIGN KEY (`scene_id`) REFERENCES `scene` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `collaborator_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '场景协作权限控制' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `scene_id` bigint(0) NOT NULL,
  `user_id` bigint(0) NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `created_at` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `scene_id`(`scene_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`scene_id`) REFERENCES `scene` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '场景协作评论' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for model
-- ----------------------------
DROP TABLE IF EXISTS `model`;
CREATE TABLE `model`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '模型ID',
  `user_id` bigint(0) NOT NULL COMMENT '上传者ID',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '模型名称',
  `file_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'OSS存储路径',
  `file_type` enum('FBX','GLTF','OBJ') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '模型格式',
  `thumbnail_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '缩略图URL',
  `created_at` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `model_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户上传的3D模型文件' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for operation_log
-- ----------------------------
DROP TABLE IF EXISTS `operation_log`;
CREATE TABLE `operation_log`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(0) NOT NULL,
  `scene_id` bigint(0) NOT NULL,
  `action` enum('add_model','delete_model','update_position') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `details` json NULL COMMENT '操作详情（JSON格式）',
  `created_at` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `scene_id`(`scene_id`) USING BTREE,
  CONSTRAINT `operation_log_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `operation_log_ibfk_2` FOREIGN KEY (`scene_id`) REFERENCES `scene` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '场景操作审计日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for scene
-- ----------------------------
DROP TABLE IF EXISTS `scene`;
CREATE TABLE `scene`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '场景ID',
  `user_id` bigint(0) NOT NULL COMMENT '创建者ID',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '场景名称',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '场景描述',
  `is_public` tinyint(1) NULL DEFAULT 0 COMMENT '是否公开',
  `created_at` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `scene_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户创建的AR场景' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for scene_model
-- ----------------------------
DROP TABLE IF EXISTS `scene_model`;
CREATE TABLE `scene_model`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `scene_id` bigint(0) NOT NULL COMMENT '场景ID',
  `model_id` bigint(0) NOT NULL COMMENT '模型ID',
  `position_x` float NOT NULL DEFAULT 0 COMMENT 'X轴坐标',
  `position_y` float NOT NULL DEFAULT 0 COMMENT 'Y轴坐标',
  `position_z` float NOT NULL DEFAULT 0 COMMENT 'Z轴坐标',
  `rotation` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '旋转角度（JSON格式）',
  `scale` float NULL DEFAULT 1 COMMENT '缩放比例',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `scene_model_unique`(`scene_id`, `model_id`) USING BTREE,
  INDEX `model_id`(`model_id`) USING BTREE,
  CONSTRAINT `scene_model_ibfk_1` FOREIGN KEY (`scene_id`) REFERENCES `scene` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `scene_model_ibfk_2` FOREIGN KEY (`model_id`) REFERENCES `model` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '场景中放置的模型实例' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '加密密码（bcrypt）',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `created_at` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '注册时间',
  `updated_at` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户账户信息' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

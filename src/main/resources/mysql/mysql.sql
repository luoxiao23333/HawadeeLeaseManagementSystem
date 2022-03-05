/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : localhost:3306
 Source Schema         : hawadee

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 05/03/2022 19:58:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for contract_info
-- ----------------------------
DROP TABLE IF EXISTS `contract_info`;
CREATE TABLE `contract_info`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `current_handler_id` int NULL DEFAULT NULL,
  `promoter_id` int NULL DEFAULT NULL,
  `status` int NOT NULL DEFAULT 1,
  `title` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` mediumblob NULL,
  `file_loc` varchar(10000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of contract_info
-- ----------------------------
INSERT INTO `contract_info` VALUES (1, 2, 1, 1, '123', 0x313233, '5f6f29a5-d07e-4a78-ba45-3faca3fdebb8MSCS_DEGREE_REQUIREMENTS_FORM_2022.pdf', '2022-03-05 14:37:33');
INSERT INTO `contract_info` VALUES (2, 2, 1, 1, '123333', 0x333231, '0ffa2dcf-af02-45c5-bf88-0ad596db3e8dMSCS_DEGREE_REQUIREMENTS_FORM_2022.pdf', '2022-03-05 17:14:04');

-- ----------------------------
-- Table structure for contract_processing_history
-- ----------------------------
DROP TABLE IF EXISTS `contract_processing_history`;
CREATE TABLE `contract_processing_history`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `contract_id` int NULL DEFAULT NULL,
  `process_user_id` int NULL DEFAULT NULL,
  `reason` mediumblob NULL,
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `status` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of contract_processing_history
-- ----------------------------

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (1, 'Directors');
INSERT INTO `department` VALUES (2, 'Law');
INSERT INTO `department` VALUES (3, 'Business');

-- ----------------------------
-- Table structure for duty
-- ----------------------------
DROP TABLE IF EXISTS `duty`;
CREATE TABLE `duty`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `department_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of duty
-- ----------------------------
INSERT INTO `duty` VALUES (1, 'general manager', 1);
INSERT INTO `duty` VALUES (2, 'law director', 2);
INSERT INTO `duty` VALUES (3, 'business stuff', 3);

-- ----------------------------
-- Table structure for duty2user
-- ----------------------------
DROP TABLE IF EXISTS `duty2user`;
CREATE TABLE `duty2user`  (
  `duty_id` int NULL DEFAULT NULL,
  `user_id` int NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of duty2user
-- ----------------------------
INSERT INTO `duty2user` VALUES (1, 1);
INSERT INTO `duty2user` VALUES (2, 2);
INSERT INTO `duty2user` VALUES (3, 3);

-- ----------------------------
-- Table structure for reimbursement_info
-- ----------------------------
DROP TABLE IF EXISTS `reimbursement_info`;
CREATE TABLE `reimbursement_info`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `current_handler_id` int NULL DEFAULT NULL,
  `promoter_id` int NULL DEFAULT NULL,
  `status` int NOT NULL DEFAULT 1,
  `title` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` mediumblob NULL,
  `file_loc` varchar(10000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reimbursement_info
-- ----------------------------
INSERT INTO `reimbursement_info` VALUES (2, 3, 1, 1, '432', 0x343332, '578076cd-5db8-47c2-82fc-2ee77ec186c5cv.pdf', '2022-03-05 17:18:32');

-- ----------------------------
-- Table structure for reimbursement_processing_history
-- ----------------------------
DROP TABLE IF EXISTS `reimbursement_processing_history`;
CREATE TABLE `reimbursement_processing_history`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `reimbursement_id` int NULL DEFAULT NULL,
  `process_user_id` int NULL DEFAULT NULL,
  `reason` mediumblob NULL,
  `create_date` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `status` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reimbursement_processing_history
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE,
  INDEX `name_2`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'David Manager', '1234', '19981486268');
INSERT INTO `user` VALUES (2, 'John Lawyer', '1234', '19981486268');
INSERT INTO `user` VALUES (3, 'Bob Stuff', '1234', '19981486268');

SET FOREIGN_KEY_CHECKS = 1;

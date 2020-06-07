//package com.dujiaoshou.service;
//
//import org.apache.ibatis.session.RowBounds;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Objects;
//
//@Service
//public class NotificationService {
//
//    @Autowired
//    private NotificationMapper notificationMapper;
//    public PaginationDTO list(Long userId, Integer page, Integer size) {
//
//        PaginationDTO<NotificationDTO> paginationDTO = new PaginationDTO<>();
//
//        Integer totalPage;
//
//        NotificationExample notificationExample = new NotificationExample();
//        //添加receiver等于userID的条件
//        notificationExample.createCriteria()
//                .andReceiverEqualTo(userId);
//        //进行数据库操作，查询消息通知的个数
//        Integer totalCount = (int) notificationMapper.countByExample(notificationExample);
//
//        //计算消息通知所占的网页数totalPage
//        if (totalCount % size == 0) {
//            totalPage = totalCount / size;
//        } else {
//            totalPage = totalCount / size + 1;
//        }
//        //所在的当前页
//        if (page < 1) {
//            page = 1;
//        }
//        //==============？？？==============
//        if (page > totalPage) {
//            page = totalPage;
//        }
//        paginationDTO.setPagination(totalPage, page);
//
//        //size*(page-1)，从offset条消息开始
//        Integer offset = size * (page - 1);
//        NotificationExample example = new NotificationExample();
//        example.createCriteria()
//                .andReceiverEqualTo(userId);
//        //添加降序排列条件，按照gmt_create
//        example.setOrderByClause("gmt_create desc");
//
//        RowBounds rowBounds = new RowBounds(offset, size); // offset起始行   size是当前页显示多少条数据
//        List<Notification> notifications = notificationMapper.selectByExampleWithRowbounds(example, rowBounds);
//
//        if (notifications.size() == 0) {
//            return paginationDTO;
//        }
//
//        List<NotificationDTO> notificationDTOS = new ArrayList<>();
//
//        //往paginationDTO插入，查询的所有通知的回复类型，"回复了问题/评论"
//        for (Notification notification : notifications) {
//            NotificationDTO notificationDTO = new NotificationDTO();
//            BeanUtils.copyProperties(notification, notificationDTO);
//            notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
//            notificationDTOS.add(notificationDTO);
//        }
//        paginationDTO.setData(notificationDTOS);
//        return paginationDTO;
//    }
//
//    //计算消息通知的未读数
//    public Long unreadCount(Long userId) {
//        NotificationExample notificationExample = new NotificationExample();
//        notificationExample.createCriteria()
//                .andReceiverEqualTo(userId)
//                .andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
//        return notificationMapper.countByExample(notificationExample);
//    }
//
//    //是否已读
//    public NotificationDTO read(Long id, User user) {
//        Notification notification = notificationMapper.selectByPrimaryKey(id);
//        if (notification == null) {
//            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
//        }
//        //接收消息的人不等于已登录的user，便报错，并避免了参数都为null的情况
//        if (!Objects.equals(notification.getReceiver(), user.getId())) {
//            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
//        }
//        //已读，设置状态为1
//        notification.setStatus(NotificationStatusEnum.READ.getStatus());
//        notificationMapper.updateByPrimaryKey(notification);
//        //设置通知消息的类型
//        NotificationDTO notificationDTO = new NotificationDTO();
//        BeanUtils.copyProperties(notification, notificationDTO);
//        notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
//        return notificationDTO;
//    }
//}
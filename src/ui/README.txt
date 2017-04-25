前端架构已经全部完成
Main是整个软件的入口，需要按照注释，完善start方法，以及两个接口方法
//ui.abstractScene里面存放的是一些抽象的父类，出于可拓展性的考虑而创建，无需修改
ui.sceneInterface存放了ui的内部scene接口，用于不同scene来请求Main执行动作，可以查看，无需修改
ui.specialScene存放了具体的各种游戏模式（PVA、PVP、Normal）的具体scene以及staticScene静态显示界面，需要逐个完善

总结：需要完成Main三个部分、ui.specialScene下所有类的开发
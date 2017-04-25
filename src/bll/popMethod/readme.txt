Popable为popMethod包的内部接口，用于规约每个具体的popmethod子类。
popmethodhub是带有bllservice的类，用于存放某次game的所有消除方式。

Attention！
需要完善bll.popMethod.allMethod包，添加所有的消除方式。
再创建PopMethodHub时，请注意具体的PopMethod的存放顺序：复合消除在前，高级消除在前。（按照消除方式优先级来存放）
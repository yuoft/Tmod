### Tmod 绿宝石工艺

* 2022/1/28
* ~~等级系统完善；op装甲修复。空间核心。村民。多功能工具挖掘问题。~~
* 2/3 等级系统优化完成。部分合成表修改，添加物品，物品修改。武器合成时添加等级。调整装备属性。添加龙晶系列。调整绿宝石锭合成。铁砧合成龙晶系列。
调整幸运商人交易顺序。
* 2/4 怪物修改：更改部分纹理，添加末影人和蜘蛛眼睛特殊渲染。修改Alex和Steve模型。调整boss方块Boss召唤。添加部分合成表。
* 2/7 boss Ai调整，boss血条使用原版样式。重写提取机更新逻辑，调整gui界面，使其可以显示物品信息。调整怪物渲染（僵尸，小白
可以在更改皮肤情况下，显示盔甲），添加末影人和蜘蛛眼睛特殊渲染。修改Kiana模组为Alex，修改Alex和Steve模组为玩家模型。村民显示职业。
修复魔法伤害不会被op装备减免问题。多功能工具挖掘等级下调，修复其无法挖掘其他模组方块问题。为工具和弓增加等级。捡起掉落物时添加等级，修复物品错误
添加“等级：0”的问题。修复附魔“以战养战”错误显示聊天信息问题。修改经验液滴掉落为随机掉落（0 - 怪物等级/4）的数量，boss生物额外掉落（0-等级/4）的大经验液滴。
* 2/8 弓箭伤害加成。工具台合成等级为（lv-10 ~ lv+5）, 捡起等级为（0 ~ 100）。武器攻击限制，指令。限制玩家使用高等级物品（无法右键，无法左键 ，
无法攻击，无法穿戴）。等级工具增加效率，弓增加伤害。添加药水合成，提取机使用岩浆加工绿宝石锭块获取熔融绿宝石桶。修复装备升级宝石无法正确提升物品等级问题。
* 2/12 属性使用修饰器更改。修复驯化狼时的血量问题。修复其他模组生物生成属性问题。新增指令：设置等级，切换信息.
* 2/14 加强火焰弹类投掷物。op剑击杀获取双倍经验。增加小白弓箭伤害。op装备无 等级，加强部分怪物远程伤害。
* 2/18 修改等级计算方式：距离玩家在16格内的怪物等级受玩家等级影响（在玩家等级的-10~+5范围内波动），否则受怪物基础等级影响（波动范围相同。基础等级默认每
隔5分钟增加一级，增加速度和当前值都可通过指令调整）。完善指令。修复部分进度错误。取消红宝石原矿经验掉落。添加新进度，调整原进度。世界数据存储。
* 2/22 完善基础等级随时间增加。添加几套新装备。添加一些原版配方。添加地狱矿物生成。
* 3/1 修复构建环境错误导致无法进入游戏（开发环境外），修复低于4级的生物不会掉落问题。
* 3/12 修复玩家等级够后无法使用低等级物品问题。修复玩家死亡和从末地返回时等级数据丢失问题。调整合成物品等级范围。修复等级查看器使用时消耗副手物品
耐久问题。修复玩家等级数据无法保存问题。修复套装效果错误触发问题。不死图腾套增加复活效果(消耗随机一件大量耐久)。
* 6/26 调整恒霜和天火的武器效果。调整空间剑的冷却效果。
* 7/27 升级gradle版本。代码优化，装备属性调整。
* 8/1 升级到Forge-2860版本。
* 8/3 变量命名调整，作物调整，【正面对决】进度调整。
* 8/6 提取机-》能力转变机：运行逻辑改为配方判断，配方有模组预加载。部分方块类简化，盔甲和装备属性调整。部分贴图修改，作物生长时间调整。
将项目编码调整为UTF-8。盔甲套装buff调整，转变机gui修改。
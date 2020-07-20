/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 * http://www.gnu.org/copyleft/gpl.html
 */

/**
 * <h1>主权预警雷达</h1>
 * <h2>简介</h2>
 * 此程序每分钟拉取监视者的通知列表，如果出现类型“StructureUnderAttack”的通知则使用推送者发送预警。
 * 可以添加多个监视者。如果多个监视者在单个监视周期收到同个通知，则不多次通知。
 * <h2>术语表</h2>
 * <ul>
 *     <li>监视者：一个EVE角色，可以访问ESI来拉取各自的通知列表</li>
 *     <li>推送者：推送的实现，可以是QQ机器人、电子邮件或者输出到网页等</li>
 *     <li>StructureUnderAttack：代表一个建筑正在被击的通知类型</li>
 * </ul>
 */
module razesoldier.structureradar {
    requires org.jetbrains.annotations;
    requires fastjson;
    requires razesoldier.esi;
    requires java.net.http;
    requires razesoldier.everadar;
    requires org.slf4j;

    opens razesoldier.structureradar to fastjson;
}

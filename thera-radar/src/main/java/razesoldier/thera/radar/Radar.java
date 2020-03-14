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

package razesoldier.thera.radar;

import com.alibaba.fastjson.JSON;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import razesoldier.thera.radar.alarm.Alarm;
import razesoldier.thera.radar.alarm.AlarmException;
import razesoldier.thera.radar.api.ApiFactory;
import razesoldier.thera.radar.api.ConnectionException;
import razesoldier.thera.radar.model.WormholeModel;

import java.util.ArrayList;
import java.util.List;

public class Radar {
    private Logger logger;
    private String watchSystemName;
    private List<Alarm> alarmList = new ArrayList<>();
    private List<Integer> recordList = new ArrayList<>();

    public Radar(@NotNull String watchSystemName, @NotNull Logger logger) {
        this.watchSystemName = watchSystemName;
        this.logger = logger;
    }

    public void addAlarm(@NotNull Alarm alarm) {
        alarmList.add(alarm);
    }

    public void echo() throws ConnectionException {
        String json = ApiFactory.newInstance().query(watchSystemName);
        List<WormholeModel> list = JSON.parseArray(json, WormholeModel.class);
        for (WormholeModel model : list) {
            if (recordList.contains(model.getId())) {
                // No repeat warning
                continue;
            }

            if (model.getJumps() == 0) {
                if (!model.getDestinationSolarSystem().getName().equals(watchSystemName)) {
                    continue;
                }
                alarm(watchSystemName, model.getDestinationSolarSystem().getName(), model.getJumps(), model.getId());
                continue;
            }
            if (model.getJumps() <= 15) {
                alarm(watchSystemName, model.getDestinationSolarSystem().getName(), model.getJumps(), model.getId());
            }
        }
    }

    private void alarm(@NotNull String watchSystemName, @NotNull String dstSystem, @NotNull Integer jumps,
                       @NotNull Integer id) {
        recordList.add(id);
        logger.info(String.format("%s is %d jumps from %s\n", dstSystem, jumps, watchSystemName));
        alarmList.forEach(alarm -> {
            try {
                alarm.sendAlarm(watchSystemName, dstSystem, jumps);
            } catch (AlarmException e) {
                logger.error(e.toString());
            }
        });
    }
}

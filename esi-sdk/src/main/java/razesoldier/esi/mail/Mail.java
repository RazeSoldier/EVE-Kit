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

package razesoldier.esi.mail;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class Mail {
    private Integer senderId;
    private List<Recipient> recipients = new ArrayList<>();
    private String subject;
    private String body;

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public void addRecipient(Recipient recipient) {
        this.recipients.add(recipient);
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBody(String body) {
        this.body = body;
    }

    Integer getSenderId() {
        return senderId;
    }

    List<Recipient> getRecipients() {
        return recipients;
    }

    String getSubject() {
        return subject;
    }

    String getBody() {
        return body;
    }

    String toJson() {
        List<RecipientModel> recipientModelList = new ArrayList<>();
        recipients.forEach(v -> {
            RecipientModel model = new RecipientModel();
            model.recipient_id = v.getId();
            model.recipient_type = v.getType().toString();
            recipientModelList.add(model);
        });
        MailModel model = new MailModel();
        model.subject = subject;
        model.body = body;
        model.recipients = recipientModelList;
        return JSON.toJSONString(model);
    }

    private static class MailModel {
        private String body;
        private String subject;
        private List<RecipientModel> recipients;

        public String getBody() {
            return body;
        }

        public String getSubject() {
            return subject;
        }

        public List<RecipientModel> getRecipients() {
            return recipients;
        }
    }

    private static class RecipientModel {
        private Integer recipient_id;
        private String recipient_type;

        public Integer getRecipient_id() {
            return recipient_id;
        }

        public String getRecipient_type() {
            return recipient_type;
        }
    }
}

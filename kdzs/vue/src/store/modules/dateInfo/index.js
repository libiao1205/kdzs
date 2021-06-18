import moment from 'moment'

const Y_M_D_Formatter = 'YYYY年MM月DD日';
const M_D_Formatter = 'MM月DD日';

const Y_M_D_H_MI_Formatter = 'YYYY年MM月DD日 HH:mm';
const M_D_H_MI_Formatter = 'MM月DD日 HH:mm';
const H_MI_Formatter = 'HH:mm';

const getters = {
    getDateNameForCell: () => (row, column, cellValue) => {
        if (!cellValue) return "";
        return moment(cellValue).format(Y_M_D_Formatter)
    },
    getDateRangeNameForCell: () => (row, column, cellValue) => {
        if (!cellValue) return "";
        let startDate = moment(cellValue).format(Y_M_D_Formatter);
        let endDate = moment(row[column.property.replace('start', 'end')]).format(M_D_Formatter);
        return `${startDate}～${endDate}`;
    },

    getDateRangeName: () => (startDate, endDate) => {
        if (!startDate || !endDate) return "";
        return `${moment(startDate).format(Y_M_D_Formatter)}～${moment(endDate).format(M_D_Formatter)}`;
    },
    getDateTimeRangeNameForCell: () => (row, column, cellValue) => {
        if (!cellValue) return "";
        let endDate = row[column.property.replace('start', 'end')];
        let startDateText = moment(cellValue).format(Y_M_D_H_MI_Formatter);
        let endDateText;
        if (moment(cellValue).format(Y_M_D_Formatter) === moment(endDate).format(Y_M_D_Formatter)) {
            endDateText = moment(endDate).format(H_MI_Formatter);
        } else {
            endDateText = moment(endDate).format(M_D_H_MI_Formatter);
        }

        return `${startDateText}～${endDateText}`;
    },
    getDateTimeRangeName: () => (startDate, endDate) => {
        if (!startDate || !endDate) return "";
        if (moment(startDate).format(Y_M_D_Formatter) === moment(endDate).format(Y_M_D_Formatter)) {
            return `${moment(startDate).format(Y_M_D_H_MI_Formatter)}～${moment(endDate).format(H_MI_Formatter)}`;
        } else {
            return `${moment(startDate).format(Y_M_D_H_MI_Formatter)}～${moment(endDate).format(M_D_H_MI_Formatter)}`;
        }
    },
    getDateTimeForList: () => (datetime,times) => {
        let date = new Date(datetime);
        let time1 = new Date(times[0]);
        let time2 = new Date(times[1]);
        let startTime = new Date(date.getFullYear(),
                                 date.getMonth(),
                                 date.getDate(),time1.getHours(),time1.getMinutes());
        let endTime = new Date(date.getFullYear(),
                               date.getMonth(),
                               date.getDate(),time2.getHours(),time2.getMinutes());
        return [startTime,endTime]
    },
    setDateTime:() => (dateInfo, timeInfo) => {
        let date = moment(dateInfo);
        let time = moment(timeInfo, H_MI_Formatter);
        date.hour(time.hour());
        date.minute(time.minute());
        return date;
    }
};

export default {
    namespaced: true,
    getters
}

public class Hour {
    private int hour;
    private int minute;

    // Constructor
    public Hour(int hour, int minute) {
        if (setHour(hour) && setMinute(minute)) {
            this.hour = hour;
            this.minute = minute;
        }
        else {
            this.hour = 0;
            this.minute = 0;
        }
    }

    public void inc() {
        if (minute < 59) {
            minute++;
        }
        else {
            minute = 0;
            if (hour < 23) {
                hour++;
            }
            else {
                hour = 0;
            }
        }
    }

    public boolean setMinute(int value) {
        if (value >= 0 && value <= 59) {
            minute = value;
            return true;
        }
        else {
            return false;
        }
    }

    public boolean setHour(int value) {
        if (value >= 0 && value <= 23) {
            hour = value;
            return true;
        }
        else {
            return false;
        }
    }


    public String toString() {
        return String.format("%02d:%02d", hour, minute);
    }
}

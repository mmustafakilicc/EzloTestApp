package com.ezlo.ezlotestapp.utils;

public class GlobalEnums {

    public enum DeviceType {
        TYPE1(1),
        TYPE2(2),
        TYPE3(3);

        private final int type;

        DeviceType(int type) {
            this.type = type;
        }

        public int getViewType() {
            return type;
        }
    }

    public enum Platform {

    }

    public enum ActionClick{
        SAVE(1),
        CANCEL(2),
        INACTIVE(3);
        private final int value;
        ActionClick(int value){
            this.value = value;
        }

        public int getValue(){
            return value;
        }
    }
}

import React, { type FC } from "react";
import { Modal, Pressable, ScrollView, Text, TouchableWithoutFeedback, View } from "react-native";

import s from "./styles";

export type DialogAction = { label: string; callback: () => void };

interface Props {
  title: string;
  description: string;
  actions: DialogAction[];
  visible: boolean;
  onClose: () => void;
}

const Dialog: FC<Props> = ({ title, description, actions, visible, onClose }) => {
  return (
    <Modal visible={visible} animationType="fade" onRequestClose={onClose} transparent accessibilityViewIsModal>
      <View style={s.background}>
        <TouchableWithoutFeedback onPress={onClose}>
          <View style={s.fill} />
        </TouchableWithoutFeedback>
        <View style={s.dialog}>
          <Text style={s.title}>{title}</Text>
          <View style={s.description}>
            <ScrollView nestedScrollEnabled>
              <Text style={s["description-content"]}>{description}</Text>
            </ScrollView>
          </View>
          <View style={s.actions}>
            {actions.map((action) => {
              return (
                <Pressable
                  style={s.action}
                  key={action.label}
                  role="button"
                  aria-label={action.label}
                  accessibilityRole="button"
                  accessibilityLabel={action.label}
                  android_ripple={{ color: "rgba(0, 0, 0, 0.1)", foreground: true }}
                  onPress={action.callback}
                >
                  <Text style={s.label}>{action.label}</Text>
                </Pressable>
              );
            })}
          </View>
        </View>
      </View>
    </Modal>
  );
};

export default Dialog;

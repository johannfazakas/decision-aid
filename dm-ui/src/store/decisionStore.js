import { EventEmitter } from "events";
import dispatcher from "../dispatcher/appDispatcher";
import actionType from "../action/actionType";

const CHANGE_EVENT = "change";

let _decisions = [];

class DecisionStore extends EventEmitter {

  addChangeListener = callback => this.on(CHANGE_EVENT, callback);
  removeChangeListener = callback => this.removeListener(CHANGE_EVENT, callback);
  emitChange = () => this.emit(CHANGE_EVENT);

  getDecisions = () => _decisions;
}

const store = new DecisionStore();

dispatcher.register(action => {
  switch (action.actionType) {

    case actionType.LOAD_DECISIONS:
      _decisions = action.decisions;
      store.emitChange();
      break;

    case actionType.DELETE_DECISION:
      _decisions = _decisions.filter(d => d.id !== action.decisionId);
      store.emitChange();
      break;

    default:
  }
});

export default store;

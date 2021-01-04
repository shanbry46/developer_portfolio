import * as React from "react";
import Autosuggest from "react-autosuggest";
import "./MedicationSuggestionBar.css";

interface IProps {
  onSelectionChosen: (value: string) => void;
  onSuggestionsFetchRequested: (searchParam: string) => Promise<string[]>;
}

interface IState {
  suggestions: string[];
  value: string;
}

class MedicationSuggestionBar extends React.Component<IProps, IState> {
  constructor(props: IProps) {
    super(props);

    this.state = {
      suggestions: [],
      value: "",
    };
  }

  private onSuggestionsFetchRequested = async ({ value }: any) => {
    this.setState({
      suggestions: await this.props.onSuggestionsFetchRequested(value),
    });
  };

  private onSuggestionsClearRequested = () => {};

  private getSuggestionValue = (suggestion: string) => {
    this.props.onSelectionChosen(suggestion);
    return suggestion;
  };

  private renderSuggestion = (suggestion: string) => <span>{suggestion}</span>;

  private onSuggestionChange = (event: any, { newValue }: any) => {
    this.setState({ value: newValue });
  };

  public render() {
    const { value, suggestions } = this.state;
    const inputProps = {
      placeholder: "Search a medication",
      value,
      onChange: this.onSuggestionChange,
    };
    return (
      <Autosuggest
        suggestions={suggestions}
        onSuggestionsFetchRequested={this.onSuggestionsFetchRequested}
        onSuggestionsClearRequested={this.onSuggestionsClearRequested}
        getSuggestionValue={this.getSuggestionValue}
        renderSuggestion={this.renderSuggestion}
        inputProps={inputProps}
      />
    );
  }
}

export default MedicationSuggestionBar;

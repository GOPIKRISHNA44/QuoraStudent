package com.quorastudent.dto;

public class AskAnEventDTO {

	private AskAquestionDTO askAquestionDTO;
	private EventDTO eventDTO;

	public AskAquestionDTO getAskAquestionDTO() {
		return askAquestionDTO;
	}

	public void setAskAquestionDTO(AskAquestionDTO askAquestionDTO) {
		this.askAquestionDTO = askAquestionDTO;
	}

	public EventDTO getEventDTO() {
		return eventDTO;
	}

	public void setEventDTO(EventDTO eventDTO) {
		this.eventDTO = eventDTO;
	}

}

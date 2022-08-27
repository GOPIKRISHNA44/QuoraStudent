export const Title = {
    mainTitle: "Quora Student",
    loginTitle: "Login",
    signUpTitle: "Sign Up",
    interestsTitle: "Choose interests",
    questionPlaceholder: "Ask a Question",
    questionBox: "What do you want to ask?",
    blogPlaceholder: "Write a blog post",
    answerPlaceholder: "Write your answer",
    eventPlaceholder: "Create an event",
    changePassword:"Change Password",
    forgotPassword:"Forgot Password"
    
}

export const sideNavItems = [
    { icon: "leaderboard", buttonName: "Leaderboard" },
    { icon: "event", buttonName: "Events" },
    { icon: "edit_note", buttonName: "Blog" },
    { icon: "question_mark", buttonName: "Question" },
]

export const toolbarIcons = [
    {
        icon: "notifications",
        useManualImg: false 
    }, 
    // {
    //     icon: "search",
    //     useManualImg: false 
    // }, 
{        icon: "account_circle",
        useManualImg: true
    }
]

export const questionIcons = [
    {
        icon: "comment"
    }, {
        icon: "search"
    }, {
        icon: "account_circle"
    }
]
export const QuillConfiguration = {
    toolbar: [
        ['bold', 'italic', 'underline', 'strike'],
        ['blockquote', 'code-block'],
        [{ list: 'ordered' }, { list: 'bullet' }],
        // [{ header: [1, 2, 3, 4, 5, 6, false] }],
        [{ color: [] }, { background: [] }],
        ['link'],
        ['clean']
    ],
}
export const sortedValues = [
    {
        value: 1, viewValue: 'By likes'
    },
    {
        value: 2, viewValue: 'By date'
    }
]

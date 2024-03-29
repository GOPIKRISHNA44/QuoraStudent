export interface LoginDetails {
    username: string;
    password: string;
    emailid: string;
}
export interface ChangeDetails {
    oldPassword: string;
    newPassword: string;
}

export interface SignUpDetails {
    username: string;
    emailid: string;
    dob: string;
    universitycode: string;
    password: string;
    avatarid:String;
}

export interface UserDetails {
    userid: number;
    interestspopup: number;
    username: string;
    password: string;
    emailid: string;
    dob: string;
    universitycode: string;
    avatarid: number;
}
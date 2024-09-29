export interface Address {
    street: string;
    city: string;
    state: string;
    zipCode: string;
    country: string;
  }
  
export interface Role{
  roleId: number;
  roleName: string;
}

export interface RegisterDTO {
  username: string;
  firstName: string;
  lastName: string;
  birthDate: Date;
  password: string;
  email: string;
  phone: string;
  address: Address;
  roles: Role[];
}
  
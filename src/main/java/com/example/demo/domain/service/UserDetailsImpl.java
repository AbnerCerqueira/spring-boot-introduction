    package com.example.demo.domain.service;


    import com.example.demo.domain.user.UserAuthenticated;
    import com.example.demo.domain.user.repository.UserRepository;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.core.userdetails.UsernameNotFoundException;
    import org.springframework.stereotype.Service;

    @Service
    public class UserDetailsImpl implements UserDetailsService {

        private final UserRepository userRepository;

        public UserDetailsImpl(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return userRepository.findByUsername(username)
                    .map(UserAuthenticated::new)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        }
    }
